package MChain;

class Matrix {
	
	private int width;
	private int height;
	private float[][] values;
	private boolean m_isWellFormed;
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isWellFormed(){
		return m_isWellFormed; 
	}
	
	public Matrix(int width, int height, float[] values)
	{
		if(values.length != width * height)
		{
			this.m_isWellFormed = false; 
			return; 
		}
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				this.values[i][j] = values[i * width + j];
			}
		}
		this.height= height; 
		this.width = width;
		this.m_isWellFormed = true;
	}
	
	public Matrix(int width, int height, float[][] values)
	{
		if(values.length != width)
		{
			this.m_isWellFormed = false; 
			return; 
		}

		this.values = values; 
		this.height= height; 
		this.width = width;
		this.m_isWellFormed = true;
	}
	
	public float at(int i, int j)
	{
		return ( this.m_isWellFormed == true ? this.values[i][j] : null );
	}
	
	public Matrix multiply(Matrix b)
	{
		if(this.width != b.getHeight())
			return null;
		
		float[][] values = new float[this.height][b.width]; 
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				for(int k = 0; k < this.height; k++)
				{
					values[i][j] += this.values[i][k] * b.values[k][j]; 
				}
			}
		}
		Matrix c = new Matrix(this.height, b.width, values);
		
		return ( c.isWellFormed()== true ? c : null);
	}
	
	public String toString()
	{
		if(!this.isWellFormed())
		{
			return null;
		}
		String str = new String();
		for(int i = 0; i < this.height; i++)
		{
			for(int j = 0; j < this.width; j++)
			{
				str += this.values[i][j] + " ";
			} 
			str += "\n";
		}
		return str;
	}
}
