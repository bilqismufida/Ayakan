    package com.method.ayakan.repository;

    import java.util.HashMap;

    import com.method.ayakan.model.Catatan;

    public class CatatanRepository {
        private HashMap<Integer, Catatan> dbCatatan = new HashMap<>();
        
        public void save(Catatan link){
            dbCatatan.put(link.getId(), link);
        }
        
        public Catatan findById(int id){
            return dbCatatan.get(id);
        }
        
        
        public HashMap<Integer, Catatan> findAll(){
            return dbCatatan;
        }
        
        public void delete(int id){
            dbCatatan.remove(id);
        }
        
        public boolean check(int id){
            return dbCatatan.containsKey(id);
        }
        public boolean isEmpty(){
            return dbCatatan.isEmpty();
        }
    }
