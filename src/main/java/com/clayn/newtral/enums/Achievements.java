package com.clayn.newtral.enums;

public enum Achievements {
    FIRST_POST("First post", "You have made your first post as an editor!"),
    TEN_POSTS("10 posts", "You have made 10 posts. Good start!"),
    FIRST_COMMENT("First comment", "You let the whole world see what you think!"),
    TEN_COMMENTS("10 comments", "You have commented 10 times!");

    private final String title;
    private final String description;

    Achievements(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
