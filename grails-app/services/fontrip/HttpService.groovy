package fontrip

import grails.transaction.Transactional
import groovyx.net.http.HTTPBuilder
import org.apache.commons.net.ftp.FTPClient
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

@Transactional
class HttpService {

    public static boolean checkUrlExist(String url){
        boolean result = false
        try {
            new HTTPBuilder( url ).get( path:'' ) { response ->
                if( response.statusLine.statusCode == 200)return true
            }
        }
        catch( e ) {
        }
        try {
            HttpGet req = new HttpGet(url);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(req);
            // validate response code, etc.
            InputStream inputStream = response.getEntity().getContent();
            if(inputStream.available()>0) return true
        }
        catch( e ) {
        }

        return result
    }
    boolean ftpDisconnect(FTPClient ftpClient){
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout()
                return ftpClient.disconnect();
            }
        } catch (Exception e) {
            return false;
        }
    }

}
