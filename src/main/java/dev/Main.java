package dev;

import dev.sigstore.rekor.client.RekorEntry;
import dev.sigstoreClient.signArtifact;
import io.github.intoto.legacy.models.Link;

import java.security.cert.Certificate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        signArtifact test = new signArtifact("/home/regulusnemea/foo.py");
        test.initSigner();
        test.sign();

        List<? extends Certificate> certs = test.result.getCertPath().getCertificates();

        List<RekorEntry> rekorEntries = test.result.getEntries();
        for (RekorEntry rekorEntry : rekorEntries) {
            System.out.println(rekorEntry.getLogIndex());
            System.out.println("Printing Rekor Entry");
            System.out.println(rekorEntry.getBodyDecoded());
        }

        for (Certificate c : certs) {
            System.out.println(c.getPublicKey());
        }
        System.out.printf("Number of certs are %d\n", certs.size());

        System.out.printf("Number of Rekor Entries %d\n", rekorEntries.size());

        String jsonString = "{ \"signed\" : {\n" +
                "    \"_type\" : \"link\",\n" +
                "    \"name\": \"write-code\",\n" +
                "    \"command\" : [\"vi\", \"foo.py\"],\n" +
                "    \"materials\": { },\n" +
                "    \"products\": {\n" +
                "      \"foo.py\": { \"sha256\": \"2a0ffef5e9709e6164c629e8b31bae0d...\" }\n" +
                "    },\n" +
                "    \"byproducts\": {\n" +
                "      \"stderr\": \"\",\n" +
                "      \"stdout\": \"\",\n" +
                "      \"return-value\": 0\n" +
                "    },\n" +
                "    \"environment\": {\n" +
                "      \"variables\": [\"\"],\n" +
                "      \"filesystem\" : \"\",\n" +
                "      \"workdir\": \"\"\n" +
                "    } \n" +
                "  },\n" +
                "  \"signatures\" : [\n" +
                "    { \"keyid\" : \"<ALICES_KEYID>\",\n" +
                "      \"sig\" : \"94df84890d7ace3ae3736a698e082e12c300dfe5aee92e...\"\n" +
                "      }\n" +
                "    ]\n" +
                "}";

        System.out.println(Link.read(jsonString));

    }
}
