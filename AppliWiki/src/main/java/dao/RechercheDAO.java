package dao;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.json.*;
import vue.FrmAjouterValeurs;

import javax.swing.*;

public class RechercheDAO {

    public static TreeMap<String, String> getRequest(String urlDestination, Component frm) {
        try {
            TreeMap<String, String> results = new TreeMap<>();
            URL url = new URL(urlDestination);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject obj = new JSONObject(response.toString());
            JSONObject arr = obj.getJSONObject("dictionary").getJSONObject("entries");
            Iterator<String> keys = arr.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                String tag = arr.getJSONObject(key).getString("tags").replace("|", "");
                String value = arr.getJSONObject(key).getString("value");
                results.put(tag, value);
            }
            return results;
        } catch (Exception e) {

        }
        return null;
    }

    public static void ajouterObservatory(String nomObservatory, String cle, Component frm) {


        try {
            URL url = new URL("http://groups.cowaboo.net/2019/group03%20-%20Amazon/public/api/observatory");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            String urlParameters = "secretKey="+cle+"&observatoryId=" + nomObservatory;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm,
                    "La clé publique et/ou secrète sont fausses !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
        }


    }

    public static void ajouterValeur(String nomObservatory, String tag, String valeur,String cle, Component frm) {


        try {
            URL url = new URL("http://groups.cowaboo.net/2019/group03%20-%20Amazon/public/api/entry");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            String urlParameters = "secretKey="+cle+"&observatoryId=" + nomObservatory + "&tags=" + tag + "&value=" + valeur;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm,
                    "La clé publique et/ou secrète sont fausses !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }


    }

    public static void transfertStellar(String clePublique, String cleSecrete, String montant, Component frm){
        try {
            URL url = new URL("http://groups.cowaboo.net/2019/group03%20-%20Amazon/public/api/user/transfer");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            String urlParameters = "public="+clePublique+"&secretKey="+cleSecrete+"&destination=GANQ3K5MUU72W7TZSVW3MISCRIGA25BUIVHMH2S4RM7H3PBXBY5BKFAJ&amount="+montant;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm,
                    "La clé publique et/ou secrète sont fausses !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public static TreeMap<String, String> getResults(String observatory, Component frm) {
        String url = "http://groups.cowaboo.net/2019/group03%20-%20Amazon/public/api/observatory?observatoryId=" + observatory;
        TreeMap<String, String> results = getRequest(url, frm);
        return results;
    }

    public static ArrayList<String> getObservatories(){
        ArrayList<String> observatories = new ArrayList<>();
        try {
            URL url = new URL("http://groups.cowaboo.net/2019/group03%20-%20Amazon/public/api/tags");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject obj = new JSONObject(response.toString());
            JSONObject arr = obj.getJSONObject("tag_list").getJSONObject("list");
            Iterator<String> keys = arr.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                observatories.add(key);
            }
        }
        catch (Exception e) {

        }

        return  observatories;
    }


}
