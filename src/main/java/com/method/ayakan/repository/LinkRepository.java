    package com.method.ayakan.repository;

    import java.util.HashMap;

    import com.method.ayakan.model.Link;

    public class LinkRepository {
        private HashMap<Integer, Link> dbLink = new HashMap<>();
        
        public void save(Link link){
            dbLink.put(link.getId(), link);
        }
        
        public Link findById(int id){
            return dbLink.get(id);
        }
        
        
        public HashMap<Integer, Link> findAll(){
            return dbLink;
        }
        
        public void delete(int id){
            dbLink.remove(id);
        }
        
        public boolean check(int id){
            return dbLink.containsKey(id);
        }
        public boolean isEmpty(){
            return dbLink.isEmpty();
        }
    }
