package dev.intotoClient;

import io.github.intoto.legacy.models.Link;

public class generateLink {
    @SuppressWarnings("deprecation")
    private Link link;

    @SuppressWarnings("deprecation")
    public Link getLink(String jsonString) {
        return Link.read(jsonString);
    }
}
