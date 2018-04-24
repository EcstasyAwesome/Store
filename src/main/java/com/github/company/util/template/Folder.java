package com.github.company.util.template;

public enum Folder {

    IMAGE("image"),
    AVATAR("avatar");

    private String folder;

    Folder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }
}