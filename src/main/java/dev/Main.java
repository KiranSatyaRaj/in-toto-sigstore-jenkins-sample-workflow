package dev;

import com.google.gson.JsonObject;
import dev.sigstoreClient.signArtifact;
public class Main {
    public static void main(String[] args) {
        signArtifact test = new signArtifact("/home/regulusnemea/foo.py");
        test.initSigner();
        test.sign();
        String metadata = test.result.toJson();
        System.out.println(metadata);
    }
}
