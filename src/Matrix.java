public class Matrix {

	float a,b,c,d;
	boolean valid = true;
	
	Matrix(float a, float b, float c, float d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		valid = Math.sqrt(Math.pow(a - c, 2) + Math.pow(b - d, 2)) < 1000;
	}
	
	Matrix(Matrix m) {
		a = m.a;
		b = m.b;
		c = m.c;
		d = m.d;
	}
	
	static Matrix mat(Matrix m1, Matrix m2) {
		return new Matrix(
				m1.a*m2.a + m1.b*m2.c, 
				m1.a*m2.b + m1.b*m2.d, 
				m1.c*m2.a + m1.d*m2.c,
				m1.c*m2.b + m1.d*m2.d);
	}
	
	@Override
	public String toString() {
		return "[" + a + "," + c + ";" + b + "," + d + "]";
	}
}