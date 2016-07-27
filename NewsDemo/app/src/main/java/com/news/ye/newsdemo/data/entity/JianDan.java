package com.news.ye.newsdemo.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YU on 2016/7/25.
 */
public class JianDan {


    /**
     * status : ok
     * count : 24
     * count_total : 56266
     * pages : 2345
     * posts : [{"id":81143,"url":"http://jandan.net/2016/07/25/daily-planet-melbournes.html","title":"探访墨尔本最声名狼藉的妓院「Daily Planet」","date":"2016-07-25 16:20:12","tags":[{"id":549,"slug":"sex","title":"SEX","description":"","post_count":1342}],"author":{"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""},"comment_count":16,"custom_fields":{"thumb_c":["http://tankr.net/s/custom/CB6K.jpg"]}}]
     */

    private String status;
    private int count;
    private int count_total;
    private int pages;
    /**
     * id : 81143
     * url : http://jandan.net/2016/07/25/daily-planet-melbournes.html
     * title : 探访墨尔本最声名狼藉的妓院「Daily Planet」
     * date : 2016-07-25 16:20:12
     * tags : [{"id":549,"slug":"sex","title":"SEX","description":"","post_count":1342}]
     * author : {"id":523,"slug":"joan","name":"肌肉桃","first_name":"","last_name":"","nickname":"肌肉桃","url":"","description":""}
     * comment_count : 16
     * custom_fields : {"thumb_c":["http://tankr.net/s/custom/CB6K.jpg"]}
     */

    private List<PostsEntity> posts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
    }

    public static class PostsEntity implements Serializable{
        private int id;
        private String url;
        private String title;
        private String date;
        /**
         * id : 523
         * slug : joan
         * name : 肌肉桃
         * first_name :
         * last_name :
         * nickname : 肌肉桃
         * url :
         * description :
         */

        private AuthorEntity author;
        private int comment_count;
        private CustomFieldsEntity custom_fields;
        /**
         * id : 549
         * slug : sex
         * title : SEX
         * description :
         * post_count : 1342
         */

        private List<TagsEntity> tags;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public AuthorEntity getAuthor() {
            return author;
        }

        public void setAuthor(AuthorEntity author) {
            this.author = author;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public CustomFieldsEntity getCustom_fields() {
            return custom_fields;
        }

        public void setCustom_fields(CustomFieldsEntity custom_fields) {
            this.custom_fields = custom_fields;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public static class AuthorEntity implements Serializable{
            private int id;
            private String slug;
            private String name;
            private String first_name;
            private String last_name;
            private String nickname;
            private String url;
            private String description;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class CustomFieldsEntity {
            private List<String> thumb_c;

            public List<String> getThumb_c() {
                return thumb_c;
            }

            public void setThumb_c(List<String> thumb_c) {
                this.thumb_c = thumb_c;
            }
        }

        public static class TagsEntity implements Serializable{
            private int id;
            private String slug;
            private String title;
            private String description;
            private int post_count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getPost_count() {
                return post_count;
            }

            public void setPost_count(int post_count) {
                this.post_count = post_count;
            }
        }
    }
}
