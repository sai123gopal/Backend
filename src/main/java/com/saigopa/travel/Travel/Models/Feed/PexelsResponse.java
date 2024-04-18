package com.saigopa.travel.Travel.Models.Feed;

import java.util.List;

public class PexelsResponse {
    private int page;
    private int per_page;
    private List<Photo> photos;
    private int total_results;
    private String next_page;

    // Getters and setters

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public String getNext_page() {
        return next_page;
    }

    public void setNext_page(String next_page) {
        this.next_page = next_page;
    }

    // Photo class for nested object
    public static class Photo {
        private int id;
        private int width;
        private int height;
        private String url;
        private String photographer;
        private String photographer_url;
        private int photographer_id;
        private String avg_color;
        private PlaceImages src;
        private boolean liked;
        private String alt;

        // Getters and setters

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPhotographer() {
            return photographer;
        }

        public void setPhotographer(String photographer) {
            this.photographer = photographer;
        }

        public String getPhotographer_url() {
            return photographer_url;
        }

        public void setPhotographer_url(String photographer_url) {
            this.photographer_url = photographer_url;
        }

        public int getPhotographer_id() {
            return photographer_id;
        }

        public void setPhotographer_id(int photographer_id) {
            this.photographer_id = photographer_id;
        }

        public String getAvg_color() {
            return avg_color;
        }

        public void setAvg_color(String avg_color) {
            this.avg_color = avg_color;
        }

        public PlaceImages getSrc() {
            return src;
        }

        public void setSrc(PlaceImages src) {
            this.src = src;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }
    }
}
