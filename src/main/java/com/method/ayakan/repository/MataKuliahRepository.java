package com.method.ayakan.repository;

import com.method.ayakan.model.MataKuliah;

public class MataKuliahRepository extends BaseRepository<MataKuliah> {
    
    @Override
    public void save(MataKuliah mk) {
        db.put(mk.getId(), mk);
    }
}