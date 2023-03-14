import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class XmlRpcClientExample {
    public static void main(String[] args) {
        String xmlRpcEndpoint = "http://your_server_url_here/rpc";
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<methodCall>" +
                "<methodName>myMethod</methodName>" +
                "<params>" +
                "<param><value><string>param1</string></value></param>" +
                "<param><value><int>2</int></value></param>" +
                "</params>" +
                "</methodCall>";

        try {
            String response = sendXmlRpcRequest(xmlRpcEndpoint, xml);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String sendXmlRpcRequest(String url, String xml) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "text/xml; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(xml))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
