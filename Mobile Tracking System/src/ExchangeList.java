public class ExchangeList {

	Myset Elist = new Myset();

	public boolean IsEmptyEL() {
		return Elist.IsEmpty();
	}

	boolean IsMemberEL(Exchange e) {
		return Elist.IsMember(e);
	}

	Exchange getEL(int i) {
		return (Exchange) Elist.Get(i);
	}

	void InsertEL(Exchange e) {
		Elist.Insert(e);
	}

	void DeleteEL(Exchange e) {
		Elist.Delete(e);
	}

	Myset UnionEL(ExchangeList el) {
		return Elist.Union(el.Elist);
	}

	Myset IntersectionEL(ExchangeList el) {
		return Elist.Intersection(el.Elist);
	}
}
