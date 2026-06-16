package com.method.ayakan.repository;

import com.method.ayakan.model.MataKuliah;
import java.util.HashMap;

public class MataKuliahRepository {
    private HashMap<Integer, MataKuliah> dbMatkul = new HashMap<>();
    
    public void save(MataKuliah mk){
        dbMatkul.put(mk.getId(), mk);
    }
    
    public MataKuliah findById(int id){
        return dbMatkul.get(id);
    }
    
    public HashMap<Integer, MataKuliah> findAll(){
        return dbMatkul;
    }
    
    public void delete(int id){
        dbMatkul.remove(id);
    }
    
    public boolean check(int id){
        return dbMatkul.containsKey(id);
    }
}
