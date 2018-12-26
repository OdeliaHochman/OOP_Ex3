package Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
	/**
	 * The department represents a collection of paths
	 * @author Efrat Cohen and Odelia Hochman
	 *
	 */
	public class Solution {

		Map<Integer, ArrayList<Path>>pathListMapper;// = new HashMap<>();
		//ArrayList<Path> pathList;

		
		public Solution()
		{
			pathListMapper = new HashMap<>();
			//pathList = new ArrayList<>();
			
		}
		
		/**
		 *  The function checks whether the path has been added to the path collection
		 * @param path
		 * @return true if the path has been added to the path collection
		 */
		public boolean add (Path path) 
		{
			if(!pathListMapper.containsKey(path.getidxPackman()))
			{
				ArrayList<Path> al = new ArrayList<>();
				pathListMapper.put(path.getidxPackman(), al);
				pathListMapper.get(path.getidxPackman()).add(path);// = al;
				
			}
			else
				pathListMapper.get(path.getidxPackman()).add(path);
			//return pathList.add(path);
			return true;
		}
		
		/**
		 * @return size of path collection
		 */
		public int size(int idxPc)
		{
			if(pathListMapper.containsKey(idxPc))
			{
			 return pathListMapper.get(idxPc).size();
			}
			else return -1;
		}
		
		/**
		 * The function checks whether the requested path is deleted from the paths collection
		 * @param path
		 * @return true if the path has been removed from the path collection
		 */
		public boolean remove(int idxPc,Path path)
		{
			if(pathListMapper.containsKey(idxPc))
			{
			 return pathListMapper.get(idxPc).remove(path);
			}
			else return false;
			//return pathList.remove(path);
		}

		/**
		 * The function checks whether the requested path is in the paths collection
		 * @param path
		 * @param idxPc-index packman
		 * @return true if the path is in the paths collection 
		 */
		public boolean contains(int idxPc, Path path)
		{
			if(pathListMapper.containsKey(idxPc))
			  return pathListMapper.get(idxPc).contains(path);
			else
				return false;
		}
		
		/**
		 * The function checks whether the resulting object is the same as the path collection
		 * @param path
		 * @param idxPc-index packman
		 * @return true if Object path is equal to the path collection
		 */
		public boolean equals(Object path,int idxPc)
		{
			return pathListMapper.get(idxPc).equals(path);
		}
		
		/**
		 * The function checks whether the path collection is empty
		 * @param idxPc - index packman
		 * @return true if the path collection is empty
		 */
		public boolean isEmpty(int idxPc)
		{
			return pathListMapper.get(idxPc).isEmpty();
		}

		/**
		 * @param idxPc-index packman
		 * @return iterator
		 */
		public Iterator<Path> iterator(int idxPc) 
		{
			
			return pathListMapper.get(idxPc).iterator();
		}
		
		/**
		 * 
		 * @param idxPc-index packman
		 * @param idxPath-index path
		 * @return index path
		 */
		public Path GetPath(int idxPc,int idxPath) 
		{
			
			return pathListMapper.get(idxPc).get(idxPath);
		}
	}



