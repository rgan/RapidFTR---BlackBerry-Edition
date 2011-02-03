package com.rapidftr.datastore;

import java.util.Enumeration;
import java.util.Vector;

import com.rapidftr.utilities.DateComparator;
import net.rim.device.api.system.Bitmap;

import com.rapidftr.model.Child;

public class Children {

	private final Vector vector;

	public Children(Vector vector) {
		this.vector = vector;
	}

	public Children(Child[] array) {
		vector = new Vector();
		for(int i=0;i<array.length;i++) {
			vector.addElement(array[i]);
		}
	}

	public int count() {
		return vector.size();
	}

	public void forEachChild(ChildAction action) {
		Enumeration elements = vector.elements();
		while(elements.hasMoreElements()){
			Child child = (Child)elements.nextElement();
			action.execute(child);
		}
	}

	public Child[] toArray() {
		Child[] array = new Child[count()];
		vector.copyInto(array);
		return array;
	}

	protected Children sort(final String[] attributes, final boolean isAscending) {
		Child[] children = toArray();
		net.rim.device.api.util.Arrays.sort(children,
				new net.rim.device.api.util.Comparator() {
					public int compare(Object o1, Object o2) {
						ChildComparator childComparator = new ChildComparator(attributes);
						return isAscending ? childComparator.compare(
								(Child) o1, (Child) o2) : childComparator
								.compare((Child) o2, (Child) o1);
					}
				});
		return new Children(children);
	}

	public Children sortByRecentlyAdded() {
        return sortByDateFieldAscending("created_at");
	}

	public Children sortByRecentlyUpdated() {
		return sortByDateFieldAscending("last_updated_at");
	}

	public Children sortByName() {
		return sort(new String[]{"name"}, true);
	}

	public Object[] getChildrenAndImages() {
		Object[] childrenAndImages = new Object[count()];
		for (int i = 0; i < count(); i++) {
			Object[] childImagePair = new Object[2];
			Child child = (Child) vector.elementAt(i);
			childImagePair[0] = child;
			childImagePair[1] = child.getThumbnail();
			childrenAndImages[i] = childImagePair;
		}
		return childrenAndImages;
	}

    private Children sortByDateFieldAscending(final String fieldName) {
        Child[] children = toArray();
        final DateComparator comparator = new DateComparator();
		net.rim.device.api.util.Arrays.sort(children,
				new net.rim.device.api.util.Comparator() {
					public int compare(Object o1, Object o2) {
						return  comparator.compare((String)((Child) o2).getField(fieldName),
                                (String) ((Child) o1).getField(fieldName));
					}
				});
		return new Children(children);
	}
}
