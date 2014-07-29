package com.redhat.osas.example;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by jottinge on 7/29/14.
 */
@WebServlet("/reflect")
public class ReflectServlet extends HttpServlet {
    final CloseableHttpClient httpclient = HttpClients.createDefault();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpGet httpget = new HttpGet("http://localhost/");
        try (CloseableHttpResponse response = httpclient.execute(httpget)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                try (InputStream instream = entity.getContent()) {
                    Scanner scanner=new Scanner(instream);
                    while(scanner.hasNext()) {
                        resp.getWriter().println(scanner.next());
                    }
                }
            }
        }
        super.doGet(req, resp);
    }
}
