import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahat on 9/23/14.
 */
public class NaivePlsParser {

    private List<URL> URLs;

    public NaivePlsParser(URL url) throws IOException {
       URLs = parseURLs(getPls(url));
    }

    private List<String> getPls(URL url) throws IOException {
        List<String> lines = new ArrayList<String>();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try
        {
            InputStream inputStream = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line = in.readLine()) != null)
                lines.add(line);

        } finally {
            conn.disconnect();
        }
        return lines;
    }

    private List<URL> parseURLs(List<String> lines) throws MalformedURLException {
        List<URL> URLs = new ArrayList<URL>();
        for(String line : lines)
        {
            line = line.trim().toLowerCase();
            if(line.indexOf("http") != -1)
            {
                URLs.add(new URL(line.substring(line.indexOf("http"))));
            }
        }
        return URLs;
    }

    public List<URL> getURLs()
    {
        return URLs;
    }
}
