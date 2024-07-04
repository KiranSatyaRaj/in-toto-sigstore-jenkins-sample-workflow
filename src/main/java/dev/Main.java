package dev;

import dev.intotoClient.GenerateLink;
import dev.sigstoreClient.SignArtifact;
import io.github.intoto.legacy.models.Link;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GenerateLink link = new GenerateLink();
        link.generateMaterials(new String[]{"/home/regulusnemea/foo.py"});
        link.dump();
        String s = link.dumpString();
        System.out.println(s);
        File file = new File("home/regulusnemea/test.link");
        try {
            FileWriter writer = new FileWriter("/home/regulusnemea/test.link");
            writer.write(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        SignArtifact test = new SignArtifact("/home/regulusnemea/test.link");
        test.initSigner();
        test.sign();
        System.out.println(test.result.toJson());
    }
}
