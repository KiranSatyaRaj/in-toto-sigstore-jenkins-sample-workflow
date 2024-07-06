package dev.intotoClient;

import io.github.intoto.legacy.models.Artifact;
import io.github.intoto.legacy.models.Link;
import jakarta.validation.constraints.NotNull;
import org.jetbrains.annotations.Contract;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("deprecated")
public class GenerateLink {
    @SuppressWarnings("deprecation")
    private final Link link;

    public GenerateLink() {
        this.link = new Link(null, null, "step", null, null, null);
    }

    public GenerateLink generateMaterials(@NotNull String[] filePaths) {
        HashMap<String, Artifact.ArtifactHash> materials;
        if (filePaths == null) {
            materials = new HashMap<>();
        } else {
            materials = setArtifacts(filePaths);
        }
        this.link.setMaterials(materials);
        return this;
    }

    public GenerateLink setProducts(@NotNull String[] filePaths) {
        HashMap<String, Artifact.ArtifactHash> products;
        if (filePaths == null) {
            products = new HashMap<>();
        } else {
            products = setArtifacts(filePaths);
        }
        this.link.setProducts(products);
        return this;
    }

    private @org.jetbrains.annotations.NotNull HashMap<String, Artifact.ArtifactHash> setArtifacts(String[] filePaths) {
        HashMap<String, Artifact.ArtifactHash> artifacts = new HashMap<>();
        String[] filenames = setFileName(filePaths);
        for (int i = 0; i < filePaths.length; i++) {
            Artifact.ArtifactHash artifactHash = new Artifact(filePaths[i]).getArtifactHashes();
            artifacts.put(filenames[i], artifactHash);
        }
        return artifacts;
    }

    public HashMap<String, Artifact.ArtifactHash> getMaterials() {
        return this.link.getMaterials();
    }



    public GenerateLink dump() {
        this.link.dump();
        return this;
    }

    public String dumpString() {
        return this.link.dumpString();
    }

    private Artifact.ArtifactHash generateArtifactHash(String filePath) {
        return new Artifact(filePath).getArtifactHashes();
    }

    @Contract(pure = true)
    private String @org.jetbrains.annotations.NotNull [] setFileName(String @org.jetbrains.annotations.NotNull [] filePaths) {
        String[] filenames = new String[filePaths.length];
        for (int i = 0; i < filePaths.length; i++) {
            String[] Path = filePaths[i].split("/");
            String fileName = Path[Path.length - 1];
            filenames[i] = fileName;
        }
        return filenames;
    }

    public GenerateLink setCommands(ArrayList<String> commands) {
        this.link.setCommand(commands);
        return this;
    }

    public GenerateLink setByProducts(String filePath) {
        HashMap<String, Object> byProducts = commandObject();
        String[] cmdArray = this.link.getCommand().toArray(new String[link.getCommand().size()]);
        try {
            Process proc = Runtime.getRuntime().exec(cmdArray, null, new File("/home/username/in-toto-workflow/"));
            StringBuilder stderr = new StringBuilder();
            StringBuilder stdout = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            stderr.append(reader.readLine());
            reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            stdout.append(reader.readLine());
            int returnVal = proc.exitValue();
            byProducts.put("stderr", stderr.toString());
            byProducts.put("stdout", stdout.toString());
            byProducts.put("return value", returnVal);
            this.link.setByproducts(byProducts);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public void setSignature() {
    }

    private HashMap<String, Object> commandObject() {
        HashMap<String, Object> object = new HashMap<>();
        object.put("stderr", null);
        object.put("stdout", null);
        object.put("return value", null);
        return object;
    }

}

class NewLink extends Link {
    public NewLink(
            HashMap<String, Artifact.ArtifactHash> materials,
            HashMap<String, Artifact.ArtifactHash> products,
            String name,
            HashMap<String, Object> environment,
            ArrayList<String> command,
            HashMap<String, Object> byproducts) {
        super(materials, products, name, environment, command, byproducts);
    }

    public void sign() {
    }
}
