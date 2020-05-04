package ro.unitbv.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ro.unitbv.dto.IdentityDTO;
import ro.unitbv.model.Identity;

public interface GenericConverter<A, B> {
	A inversConvert(B object);

	B directConvert(A object);

	default List<A> inversConvertAll(List<B> dtos) {
		Objects.requireNonNull(dtos, "Your dtos list is null.");
		List<A> objects = new ArrayList<A>();
		for (B dto : dtos) {
			objects.add(inversConvert(dto));
		}
		return objects;
	}

	default List<B> directConvertAll(List<A> objects) {
		Objects.requireNonNull(objects, "Your objects list is null.");
		List<B> dtos = new ArrayList<B>();
		for (A object : objects) {
			dtos.add(directConvert(object));
		}
		return dtos;
	}

}
