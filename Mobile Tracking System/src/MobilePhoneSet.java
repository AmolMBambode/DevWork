public class MobilePhoneSet {
	Myset phoneList = new Myset();

	boolean IsEmptyMPS() {
		return phoneList.IsEmpty();
	}

	boolean IsMemberMPS(MobilePhone o) {
		return phoneList.IsMember(o);
	}

	MobilePhone getMPS(int i) {
		return (MobilePhone) phoneList.Get(i);
	}

	void InsertMPS(int o, boolean s, Exchange ex) {
		MobilePhone mpo = new MobilePhone(o);
		mpo.state = s;
		mpo.e = ex;
		phoneList.Insert(mpo);
	}

	void DeleteMPS(int o) {
		for (int i = 0; i < phoneList.ll.size(); i++)
			if (this.getMPS(i).num == o)
				phoneList.Delete(this.getMPS(i));
	}

	Myset UnionMPS(MobilePhoneSet a) {
		return phoneList.Union(a.phoneList);
	}

	Myset IntersectionMPS(MobilePhoneSet a) {
		return phoneList.Intersection(a.phoneList);
	}

}
