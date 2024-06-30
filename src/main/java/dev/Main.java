package dev;

import dev.sigstoreClient.signArtifact;
public class Main {
    public static void main(String[] args) {
        signArtifact test = new signArtifact("/home/regulusnemea/foo.py");
        test.initSigner();
        test.sign();
        System.out.println(test.result.toString());
    }
}
