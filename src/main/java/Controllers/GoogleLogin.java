/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import DAO.AccountDAO;
import DAO.UserDAO;
import Models.Account;
import Models.GoogleAccount;
import Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import constant.Iconstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author Duy Khanh
 */
public class GoogleLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            response.sendRedirect("login.jsp");
            return;
        }

        String accessToken = getToken(code);
        GoogleAccount googleUser = getUserInfo(accessToken);

        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();

        if (accountDAO.checkGoogleExists(googleUser.getEmail())) {
            Account accGG = accountDAO.getAccIDByGG(googleUser.getEmail());
            Cookie userCookie = new Cookie("accountID", String.valueOf(accGG.getAccountID()));
            userCookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(userCookie);
            response.sendRedirect("home.jsp");
        } else {
            Account accGG = accountDAO.getAccIDByGG(googleUser.getEmail());
            userDAO.insertGoogleAcc(googleUser);
            Cookie userCookie = new Cookie("accountID", String.valueOf(accGG.getAccountID()));
            userCookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(userCookie);
            response.sendRedirect("home.jsp");
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException {

        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
                                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                                .add("code", code)
                                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                                .build()
                )
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

        return accessToken;

    }

    public static GoogleAccount getUserInfo(final String accessToken) throws ClientProtocolException, IOException {

        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;

        String response = Request.Get(link).execute().returnContent().asString();

        GoogleAccount googlePojo = new Gson().fromJson(response, GoogleAccount.class);

        return googlePojo;

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
