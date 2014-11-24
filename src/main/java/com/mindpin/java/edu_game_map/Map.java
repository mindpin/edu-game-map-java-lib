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
    public List<Object> jump;
    // for json end

    // for logic
    public HashMap<String, Node> hash_nodes = new HashMap<String, Node>();
    // for logic end

    static public Map from_json(String json) {
        Map map = new Gson().fromJson(json, Map.class);
        init_hash_nodes(map);

        init_nodes_parent(map);
        return map;
    }

    private static void init_nodes_parent(Map map) {
        for (Relation relation : map.relations) {
            Node node = map.hash_nodes.get(relation.child);
            node.parent = map.hash_nodes.get(relation.parent);
        }

        for (Node node : map.nodes){
            if(node.parent == null) {
                map.begin_node = node;
                break;
            }
        }
    }

    private static void init_hash_nodes(Map map) {
        for (Node node : map.nodes) {
            map.hash_nodes.put(node.id, node);
        }
    }
}
