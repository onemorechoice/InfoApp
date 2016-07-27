package com.news.ye.newsdemo.data.entity;

import java.util.List;

/**
 * Created by YU on 2016/6/16.
 */
public class MeiziData {
    /**
     * error : false
     * results : [{"_id":"5760b303421aa940e70aa911","createdAt":"2016-06-15T09:44:35.925Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-15T11:55:46.992Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz6g6wppj20ms0xp13n.jpg","used":true,"who":"龙龙童鞋"},{"_id":"5760b2ee421aa940eb4e0f81","createdAt":"2016-06-15T09:44:14.179Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-16T12:19:00.930Z","source":"web","type":"福利","url":"http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz7s830fj20gg0o00y5.jpg","used":true,"who":"龙龙童鞋"},{"_id":"575e0824421aa9296bddf5a4","createdAt":"2016-06-13T09:11:00.530Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-14T11:52:47.320Z","source":"web","type":"福利","url":"http://ww3.sinaimg.cn/mw690/81309c56jw1f4sx4ybttdj20ku0vd0ym.jpg","used":true,"who":"龙龙童鞋"},{"_id":"575cbba1421aa96b24382520","createdAt":"2016-06-12T09:32:17.746Z","desc":"跟上一个是一个系列的。","publishedAt":"2016-06-13T11:38:17.247Z","source":"web","type":"福利","url":"http://ww4.sinaimg.cn/mw690/9844520fjw1f4fqrpw1fvj21911wlb2b.jpg","used":true,"who":"龙龙童鞋"},{"_id":"575cbb83421aa96b20caface","createdAt":"2016-06-12T09:31:47.329Z","desc":"直接看图，，不用看描述。","publishedAt":"2016-06-12T12:04:04.308Z","source":"web","type":"福利","url":"http://ww4.sinaimg.cn/mw690/9844520fjw1f4fqribdg1j21911w0kjn.jpg","used":true,"who":"龙龙童鞋"},{"_id":"5757975b421aa90eca080dd5","createdAt":"2016-06-08T11:56:11.8Z","desc":"6.8","publishedAt":"2016-06-08T12:39:36.270Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f4nog8tjfrj20eg0mgab7.jpg","used":true,"who":"daimajia"},{"_id":"575640bf421aa9759750aee4","createdAt":"2016-06-07T11:34:23.596Z","desc":"隐藏福利","publishedAt":"2016-06-07T11:43:18.947Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f4mi70ns1bj20i20vedkh.jpg","used":true,"who":"代码家"},{"_id":"575446dd421aa948eea75a32","createdAt":"2016-06-05T23:35:57.64Z","desc":"怎么会有一只狗","publishedAt":"2016-06-06T12:24:22.149Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f4kron1wqaj20ia0rf436.jpg","used":true,"who":"代码家"},{"_id":"5750f8a7421aa95653f1b92f","createdAt":"2016-06-03T11:25:27.781Z","desc":"周五啦！","publishedAt":"2016-06-03T11:42:44.370Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034gw1f4hvgpjjapj20ia0ur0vr.jpg","used":true,"who":"代码家"},{"_id":"574f9b58421aa910b3910aef","createdAt":"2016-06-02T10:35:04.323Z","desc":"6.3","publishedAt":"2016-06-02T11:30:26.566Z","source":"api","type":"福利","url":"http://ac-OLWHHM4o.clouddn.com/4063qegYjlC8nx6uEqxV0kT3hn6hdqJqVWPKpdrS","used":true,"who":"CoXier"}]
     */
    private boolean error;
    /**
     * _id : 5760b303421aa940e70aa911
     * createdAt : 2016-06-15T09:44:35.925Z
     * desc : 直接看图，，不用看描述。
     * publishedAt : 2016-06-15T11:55:46.992Z
     * source : web
     * type : 福利
     * url : http://ww1.sinaimg.cn/mw690/692a6bbcgw1f4fz6g6wppj20ms0xp13n.jpg
     * used : true
     * who : 龙龙童鞋
     */

    private List<Meizi> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Meizi> getResults() {
        return results;
    }

    public void setResults(List<Meizi> results) {
        this.results = results;
    }

}
