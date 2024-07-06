package dev;

import dev.intotoClient.GenerateLink;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String username = System.getenv("USERNAME");
        ArrayList<String> cmdArray = new ArrayList<>();
        cmdArray.add("touch");
        cmdArray.add("foo.py");
        GenerateLink link = new GenerateLink();
        link.generateMaterials(new String[]{})
                .setCommands(cmdArray)
                .setByProducts("/home/" + username + "/in-toto-workflow/")
                .setProducts(new String[]{"/home/" + username + "/in-toto-workflow/foo.py"})
                .dump();
//        SignArtifact test = new SignArtifact("/home/username/workstation/github/in-toto/in-toto-sigstore-jenkins-sample-workflow/");
//        test.initSigner();
//        test.sign();
//        System.out.println(test.result.toJson());
    }
}
