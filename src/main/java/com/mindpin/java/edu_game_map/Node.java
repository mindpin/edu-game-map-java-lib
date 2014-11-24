package com.mindpin.java.edu_game_map;

import java.util.List;

/**
 * Created by dd on 14-11-20.
 */
public class Node {
    public String id;
    public String name;
    public Map map;
//    public Map jump_to_map; // 此 node 可以跳转到的其他 map，不能是他所属的 map
    public List<Node> parents;
    public List<Node> children;
    public List<Node> ancestors; // node 的所有祖先节点（不包括它自己）
    public List<Node> descendants; // node 的所有子孙节点（不包括它自己）

    public boolean is_begin_node; // node 是否起始节点（一个 map 一般只有一个起始节点。少数情况下有多个。没有父节点的节点就是起始节点）
    public String jump_to_map_id;

    public boolean is_learned_by(User user){ // 指定用户是否学完了该节点
        // todo
        return false;
    }
    public boolean can_be_learn_by(User user) { //=> 查询此节点是否可被指定用户学习
        return false;
    }

    // for logic
    public Node parent = null;
    public Map jump_to_map(){
        return Map.find(jump_to_map_id);
    }
}
