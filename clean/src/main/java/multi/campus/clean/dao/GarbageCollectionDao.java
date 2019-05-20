package multi.campus.clean.dao;

import java.util.List;

import multi.campus.clean.domain.GarbageCollection;

public interface GarbageCollectionDao extends CrudDao<GarbageCollection, Integer> {
	List<GarbageCollection> getUserCollection(String userid) throws Exception;
}
