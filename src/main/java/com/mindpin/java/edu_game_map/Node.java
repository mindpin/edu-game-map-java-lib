package com.mindpin.java.edu_game_map;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dd on 14-11-20.
 */
public class Node {
    public String id;
    public String name;
    public Map map;
    public List<Node> parents = new ArrayList<Node>();
    public List<Node> children = new ArrayList<Node>();

    //    public boolean is_begin_node; // node 是否起始节点（一个 map 一般只有一个起始节点。少数情况下有多个。没有父节点的节点就是起始节点）
    public String jump_to_map_id;

    public boolean is_learned_by(User user) { // 指定用户是否学完了该节点
        String learned_json_by_user = get_learned_json(map.id, user.id);
        if(learned_json_by_user == null)
            return false;
        LearnedData learned = new Gson().fromJson(learned_json_by_user, LearnedData.class);
        // 理论上获取的都是对应user的json 不用再判断用户了
        if(learned.learned_nodes != null && learned.learned_nodes.contains(id))
            return true;
        return false;
    }

    public boolean can_be_learn_by(User user) { //=> 查询此节点是否可被指定用户学习
        // 当父节点都被学习之后，此点必然可学
        boolean b = true;
        for (Node parent : parents){
            b = b && parent.is_learned_by(user);
        }
        return b;
    }

    // for logic
//    public Node parent = null;
    public Map jump_to_map() {
        return Map.find(jump_to_map_id);
    }

    public boolean is_begin_node() {
        return map.begin_node == this;
    }

    public List<Node> ancestors() {
        List ancestors = new ArrayList();
        for (Node parent : parents) {
            ancestors.add(parent);
            ancestors.addAll(parent.ancestors());
        }
        removeDuplicate(ancestors);
        if (ancestors.contains(this))
            ancestors.remove(this);
        return ancestors;
    }

    public List<Node> descendants() {
        List descendants = new ArrayList();
        for (Node child : children) {
            descendants.add(child);
            descendants.addAll(child.descendants());
        }
        removeDuplicate(descendants);
        if (descendants.contains(this))
            descendants.remove(this);
        return descendants;
    }

    private String get_learned_json(String map_id, String user_id) {
        // todo need change to http api
        return "{\n" +
                "  \"user\" : \"1\",\n" +
                "  \"map\"  : \"m1\",\n" +
                "  \"learned_nodes\" : [\n" +
                "    \"1\",\n" +
                "    \"2\",\n" +
                "    \"4\",\n" +
                "    \"5\",\n" +
                "    \"3\"\n" +
                "  ]\n" +
                "}";
    }

    public static void removeDuplicate(List arlList) {
        HashSet h = new HashSet(arlList);
        arlList.clear();
        arlList.addAll(h);
    }

    class LearnedData {
        public String user;
        public String map;
        public List<String> learned_nodes;
    }
}
