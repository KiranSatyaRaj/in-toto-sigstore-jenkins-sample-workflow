package dev.sigstoreClient;

import dev.sigstore.KeylessSigner;
import dev.sigstore.bundle.Bundle;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SignArtifact {
    // funcy is functionary
    private KeylessSigner funcy;
    public Bundle result;
    Path testArtifact;

    public SignArtifact(String filePath) {
        this.testArtifact = Paths.get(filePath);
    }

    public void initSigner() {
        try {
            this.funcy = KeylessSigner.builder().sigstorePublicDefaults().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void signArtifact() {
        try {
            this.result = this.funcy.signFile(testArtifact);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
