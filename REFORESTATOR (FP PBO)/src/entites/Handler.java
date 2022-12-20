package entites;

import java.util.ArrayList;

	// class untuk menambah dan menghapus entity yang ada
public class Handler {
	private ArrayList<SuperEntityObject> entities = new ArrayList<SuperEntityObject>();
	
	public void add(SuperEntityObject newEntity) {
		entities.add(newEntity);
	}
	
	public void remove(int index) {
		entities.remove(index);
	}
	
	public void clear() {
		entities.clear();
	}
	
	public ArrayList<SuperEntityObject> getEntities(){
		return entities;
	}

	public void update() {
		for(SuperEntityObject x : entities) {
			x.update();
		}
	}
}
