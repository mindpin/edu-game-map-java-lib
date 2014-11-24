package com.mindpin.java.edu_game_map;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dd on 14-11-20.
 */
public class Map {
    public String json;
    public String map_id;
    public String map_name;
    public boolean is_published;
    public Node begin_node;

    // for json
    public String id;
    public List<Node> nodes;
    // todo
    public List<Relation> relations;
    public List<JumpObject> jump;
    // for json end

    // for logic
    public HashMap<String, Node> hash_nodes = new HashMap<String, Node>();
    // for logic end

    private void init() {
        init_nodes();
        maps.put(id, this);
    }

    private void init_nodes_map() {
        for (Node node : nodes) {
            node.map = this;
        }
    }

    private void init_nodes() {
        init_nodes_hash();
        init_nodes_map();
        init_nodes_parent();
        init_nodes_jump();
    }

    private void init_nodes_jump() {
        if (jump != null) {
            for (JumpObject obj : jump) {
                hash_nodes.get(obj.node).jump_to_map_id = obj.map;
            }
        }
    }

    private void init_nodes_parent() {
        if (relations != null) {
            for (Relation relation : relations) {
                Node node = hash_nodes.get(relation.child);
                Node parent = hash_nodes.get(relation.parent);
                node.parents.add(parent);
                parent.children.add(node);
            }

            for (Node node : nodes) {
                if (node.parents.size() == 0) {
                    begin_node = node;
                    break;
                }
            }
        }
    }

    private void init_nodes_hash() {
        for (Node node : nodes) {
            node.map = this;
            hash_nodes.put(node.id, node);
        }
    }

    static public Map from_json(String json) {
        Map map = new Gson().fromJson(json, Map.class);
        map.init();
        return map;
    }

    static HashMap<String, Map> maps = new HashMap<String, Map>();

    static public Map find(String id) {
        return maps.get(id);
    }
}
