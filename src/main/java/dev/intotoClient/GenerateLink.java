package dev.intotoClient;

import io.github.intoto.legacy.models.Artifact;
import io.github.intoto.legacy.models.Link;
import jakarta.validation.constraints.NotNull;
import org.jetbrains.annotations.Contract;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecated")
public class GenerateLink {
    @SuppressWarnings("deprecation")
    private final Link link;

    public GenerateLink() {
        this.link = new Link(null, null, "step", null, null, null);
    }

    public void generateMaterials(@NotNull String[] filePaths) {
        String[] path = filePath.split("/");
        String filename = path[path.length - 1];
        HashMap<String, Artifact.ArtifactHash> materials = new HashMap<>();
        Artifact.ArtifactHash artifactHash = new Artifact(filePath).getArtifactHashes();
        materials.put(filename, artifactHash);
        this.link.setMaterials(materials);
    }

    public HashMap<String, Artifact.ArtifactHash> getMaterials() {
        return this.link.getMaterials();
    }



    public void dump() {
        this.link.dump();
    }

    public String dumpString() {
        return this.link.dumpString();
    }

    private Artifact.ArtifactHash generateArtifactHash(String filePath) {
        return new Artifact(filePath).getArtifactHashes();
    }

    @Contract(pure = true)
    private String[] setFileName(String @org.jetbrains.annotations.NotNull [] filePaths) {
        String[] filenames = new String[filePaths.length];
        for (String filePath: filePaths) {

        }
    }

}
