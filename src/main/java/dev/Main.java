package dev;

import dev.intotoClient.GenerateLink;
import dev.sigstoreClient.SignArtifact;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String username = System.getenv("USERNAME");
        ArrayList<String> cmdArray = new ArrayList<>();
        cmdArray.add("touch");
        cmdArray.add("foo.py");
        GenerateLink link = new GenerateLink();
        String linkname = "test.link";
        link.generateMaterials(new String[]{})
                .setCommands(cmdArray)
                .setByProducts("/home/" + username + "/in-toto-workflow/")
                .setProducts(new String[]{"/home/" + username + "/in-toto-workflow/foo.py"})
                .dump(linkname);

//        System.out.println(link);
        SignArtifact test = new SignArtifact("/home/"+ username + "/workstation/github/in-toto/in-toto-sigstore-jenkins-sample-workflow/" + linkname);
        test.initSigner();
        test.signArtifact();
//        PublicKey pubKey = test.result.getCertPath().getCertificates().getFirst().getPublicKey();
        System.out.println(test.result.getCertPath().getCertificates().getFirst());

    }
}
