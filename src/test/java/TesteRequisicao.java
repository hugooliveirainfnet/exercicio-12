import org.example.Main;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

public class TesteRequisicao {

    @Test
    public void requisicaoValida() throws IOException {
        String uri = "https://api.agify.io/?name=hugo";

        HttpURLConnection conn = Main.getRequest(uri);

        Assert.assertEquals(200, conn.getResponseCode());
    }
}
