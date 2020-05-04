package ro.unitbv.util;

public interface GenericConverter<A, B> {
	A inversConvert(B object);

	B directConvert(A object);
}
