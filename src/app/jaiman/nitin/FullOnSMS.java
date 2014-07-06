package app.jaiman.nitin;




import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class FullOnSMS {
	private final String LOGIN_URL    = "http://www.sendsmsnow.com/index.php";
	private final String SEND_SMS_URL_sendsms = "http://www.sendsmsnow.com/membprev.php";
private final String SEND_SMS_NEXT = "http://www.sendsmsnow.com/send.php";
private final String LOGOUT_URL_sendsms = "http://www.sendsmsnow.com/logout.php";


private String mobileNo;
private String password;
private DefaultHttpClient httpclient;
FullOnSMS(String username,String password){
this.mobileNo = username;
this.password = password;
httpclient = new DefaultHttpClient();
}

public String isLoggedIn() throws IOException {
// POST is being used because credentials are sent using method post

HttpPost httpost = new HttpPost(LOGIN_URL);
// use livehttp header to find parameter names 
List<NameValuePair> nlist = new ArrayList<NameValuePair>();
nlist.add(new BasicNameValuePair("uname", mobileNo));
nlist.add(new BasicNameValuePair("upass", password));
httpost.setEntity(new UrlEncodedFormEntity(nlist));
// Execute request
HttpResponse response = this.httpclient.execute(httpost);
//get response entity
HttpEntity entity = response.getEntity();
        if (entity != null) {
      String slurpui1=   "entity " + slurp(entity.getContent(), 10000000);
      
            System.out.println("entity " + response.getStatusLine().getStatusCode());

        return slurpui1;
        }
return "0";
}

public String sendSMS(String toMobile,String message,String countrycode) throws IOException {
HttpPost httpost = new HttpPost(SEND_SMS_URL_sendsms);
List<NameValuePair> nlist = new ArrayList<NameValuePair>();
nlist.add(new BasicNameValuePair("phone","0"));
nlist.add(new BasicNameValuePair("country",countrycode));
nlist.add(new BasicNameValuePair("number", toMobile));
nlist.add(new BasicNameValuePair("from","dragoncruise1"));
                nlist.add(new BasicNameValuePair("message", message));
             
                httpost.setEntity(new UrlEncodedFormEntity(nlist));
HttpResponse response = this.httpclient.execute(httpost);
HttpEntity entity = response.getEntity();
if(entity != null) {
                     String slurpui2=   "entity " + slurp(entity.getContent(), 10000000);
                        System.out.println("entity " + response.getStatusLine().getStatusCode());
return slurpui2;
}
return "0";
}

public boolean sendSMSnextstep(String toMobile,String message,String countrycode) throws IOException {
HttpPost httpost = new HttpPost(SEND_SMS_NEXT);
List<NameValuePair> nlist = new ArrayList<NameValuePair>();
nlist.add(new BasicNameValuePair("advanced","0"));
nlist.add(new BasicNameValuePair("adv_hour","1"));
nlist.add(new BasicNameValuePair("adv_minute","0"));
nlist.add(new BasicNameValuePair("adv_ampm","0"));
nlist.add(new BasicNameValuePair("do","sendit"));
nlist.add(new BasicNameValuePair("country",countrycode));



nlist.add(new BasicNameValuePair("number", toMobile));
nlist.add(new BasicNameValuePair("phone","0"));
nlist.add(new BasicNameValuePair("from","dragoncruise1"));
                nlist.add(new BasicNameValuePair("message", message));
                nlist.add(new BasicNameValuePair("provid","0"));
             
                httpost.setEntity(new UrlEncodedFormEntity(nlist));
HttpResponse response = this.httpclient.execute(httpost);
HttpEntity entity = response.getEntity();
if(entity != null) {
                        System.out.println("entity " + slurp(entity.getContent(), 10000000));
                        System.out.println("entity " + response.getStatusLine().getStatusCode());
return true;
}
return false;
}
public boolean logoutSMS() throws IOException {
HttpGet httpGet = new HttpGet(LOGOUT_URL_sendsms);
HttpResponse response;
response = this.httpclient.execute(httpGet);
HttpEntity entity = response.getEntity();
if (entity != null) {
System.out
.println("entity " + slurp(entity.getContent(), 10000000));
System.out.println("entity "
+ response.getStatusLine().getStatusCode());
return true;
}
return false;
}

public static String slurp(final InputStream is, final int bufferSize)
{
 final char[] buffer = new char[bufferSize];
 final StringBuilder out = new StringBuilder();
 try {
   final Reader in = new InputStreamReader(is, "UTF-8");
   try {
     for (;;) {
       int rsz = in.read(buffer, 0, buffer.length);
       if (rsz < 0)
         break;
       out.append(buffer, 0, rsz);
     }
   }
   finally {
     in.close();
   }
 }
 catch (UnsupportedEncodingException ex) {
   /* ... */
 }
 catch (IOException ex) {
     /* ... */
 }
 return out.toString();
}

/**
* @param args
*/
}

