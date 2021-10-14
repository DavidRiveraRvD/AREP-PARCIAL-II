/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.eci.arep;

import org.json.JSONObject;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 *
 * @author Usuario
 */
public class App {
    private static Calculadora calcular = new Calculadora(); 
    public static void main(String[] args) {
        port(getPort());
        get("/tan", (req, res) -> inputDataPage(req, res,"tan"));
        get("/sqtr", (req, res) -> inputDataPage(req, res,"sqtr"));
    }

    private static JSONObject inputDataPage(Request req, Response res,String operation) {
        double number = Double.parseDouble(req.queryParams("value"));
        JSONObject obj = new JSONObject();
        obj.put("operation",operation);
        obj.put("input",number);
        obj.put("output",(operation.equals("tan"))? calcular.tangente(number) : calcular.raiz(number));
        return obj;
    }

   static int getPort() {
       if (System.getenv("PORT") != null) {
           return Integer.parseInt(System.getenv("PORT"));
       }
       return 3456;
   }
}