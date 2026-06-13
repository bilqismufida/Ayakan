package com.method.ayakan.model;

public class Link {
    int id;
    String judulLink;
    String url;

    public Link(int id, String judulLink, String url) {
        this.id = id;
        this.judulLink = judulLink;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getJudulLink() {
        return judulLink;
    }

    public void setJudulLink(String judulLink) {
        this.judulLink = judulLink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void getInfo() {
        System.out.println("["+id+"] " + judulLink + " - " + url);
    }

    @Override
    public String toString() {
        return "Link{" + "id=" + id + ", judulLink=" + judulLink + ", url=" + url + '}';
    }
    
    
}