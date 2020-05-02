package eltech.DM2020.BigNum;

import java.math.BigInteger;
import java.util.*;

	/**
	* Класс, который позволяет манипулировать с большими натуральными числами + {0}
	* @version 0.05
	* @author Сычев Александр, Яловега Никита, Семенов Алексей, Деменьтев Дмитрий, Кашапова Ольга, Цветков Nван, Хайруллов Айрат, Муродов Ахмад
	*/
public class BigN
{
	/*Само число хранится в value - это список. В 0ой ячейке младший разряд, в 1 больше и т.д.
	Например, число 36004256360, в 0ой - 360, в 1ой - 256, во 2ой - 4, в 3ей - 36*/
	private ArrayList<Integer> value = new ArrayList<Integer>();

	private BigN(){}

	/**
	* Конструктор, с помощью которого можно ввести большое натуральное число
	* Если строка src пустая, то в value будет 0 элементов
	*
	* @param String src - представление большого натурального числа в виде строки
	*
	* @version 1.3
	* @author Сычев Александр
	*/
	public BigN(String src) throws IllegalArgumentException
	{
		int n, i;
		src = src.trim();
		n = src.length();
		if (src.charAt(0) == '-')
			throw new IllegalArgumentException("В натуральных числа + {0} не может быть отрицательных\n");
		if(n % Constants.digits == 1)
		{
			src = "00" + src;
			n+=2;
		}
		if(n % Constants.digits == 2)
		{
			src = "0" + src;
			n++;
		}
		for (i = 0; i < n-3 && Integer.valueOf(src.substring(i, i+3)) == 0; i+=3);
		src = src.substring(i, n);
		n = src.length();
		for(i = 0; i <= n-3; i+=3)
			value.add(Integer.valueOf(src.substring(i, i+3)));
		Collections.reverse(value);
	}

	/**
	* Сложение 2-x больших целых чисел. Вернёт при сложении НОВОЕ большое целое число
	*
    * @param BigN other - число, которое прибавляется к исходному
    * @return BigN result - новое число, получающееся в результате сложения
	*
	* @version 1
	* @author Сычев Александр
	*/
	public BigN add(BigN other)
	{
		BigN buffBigN = new BigN();
		int over, n1, n2, i, j, buff1, buff2, maxCell, n;
		maxCell = 1;
		for(i = 0; i < Constants.digits; i++)
			maxCell *= 10;
		n1 = this.value.size();
		n2 = other.value.size();
		n = Math.max(n1, n2);
		for(i = 0, j = 0, over = 0; i < n || j < n; over = (buff1 + buff2+over)/maxCell, i++, j++)
		{
			if (i < n1)
				buff1 = value.get(i);
			else
				buff1 = 0;
			if(j < n2)
				buff2 = other.value.get(j);
			else
				buff2 = 0;
			buffBigN.value.add((buff1 + buff2 + over) % maxCell);
		}
		if(over != 0)
			buffBigN.value.add(over);
		return buffBigN;
	}

    /**
    *  Разность двух больших натуральных чисел O(this.value.size())
    *  Если вычитаемое больше уменьшаемого, то бросит исключение
	* 
    *  @param BigN other - число, которое вычитаем из исходного
    *  @return BigN result - новое число, получающееся в результате вычитания
    *
    *  @version 0.1
    *  @author Яловега Никита
    */
    public BigN subtract(BigN other) throws ArithmeticException
    {
        int base = 1000;
        int i, j, carry, cur;
        BigN result = this.clone();

        if (this.isMoreOrEquals(other))
        {
            carry = 0;
            for (i = 0; i < other.value.size() || carry != 0; ++i)
            {
                cur = result.value.get(i) - (carry + (i < other.value.size() ? other.value.get(i) : 0));
                carry = cur < 0 ? 1 : 0;
                if (carry == 1)
                    result.value.set(i, cur+base);
                else
                    result.value.set(i, cur);
            }

            for (i = result.value.size()-1; result.value.get(i) == 0 && i > 0; --i)
                result.value.remove(i);
        }
        else
            throw new ArithmeticException("Вычитание невозможно в натуральных числах\n");
        return result;
    }

	/**
	* Вывод большого натурального числа в виде строки
	* Если в value нуль элементов, то вернёт пустую строку
	*
    * @return Представление числа в виде строки
	*
	* @version 1
	* @author 
	*/
	@Override
	public String toString()
	{
		int i;
		Collections.reverse(value);
		StringBuilder builder = new StringBuilder();
		for(i = 0; i < value.size(); i++)
			if(i != 0)
				builder.append( value.get(i)>=100?value.get(i).toString():(value.get(i)>=10?"0"+value.get(i).toString():"00"+value.get(i).toString()) );
			else
				builder.append(value.get(i).toString());
		Collections.reverse(value);
		return builder.toString();
	}

    /**
     * Умножение двух больших натуральных чисел. O(this.value.size()*other.value.size())
     *
     * @param BigN other - число, на которое нужно умножить исходное
     * @return BigN result - новое число, получающееся в результате умножения
     *
     * @version 0.3333
     * @author Яловега Никита
     */
     public BigN multiply(BigN other)
     {
        int base = 1000;
        BigN result = new BigN();
        int i, j, carry, cur;

        for (i = 0; i < this.value.size() + other.value.size(); ++i)
            result.value.add(0);

        for (i = 0; i < this.value.size(); ++i)
            for (j = 0, carry = 0; j < other.value.size() || carry != 0; ++j)
            {
                cur = result.value.get(i+j) + this.value.get(i) * (j < other.value.size() ? other.value.get(j) : 0) + carry;
                result.value.set(i+j, cur % base);
                carry = cur / base;
            }
			 
        for (i = result.value.size()-1; result.value.get(i) == 0 && i > 0; --i)
     	    result.value.remove(i);
        return result;
 	}

    /**
    * Сравнение двух больших натуральных чисел.
    *
    * @param BigN other - второе число для сравнения с исходным
    * @return int - 0 если равны, -1 если меньше other, 1 если больше other
    *
    * @version 2
    * @author Яловега Никита, Семенов Алексей
    */
    public int compareTo(BigN other)
    {
		String src, compared;
		int buffCompared;
		src = this.toString();
		compared = other.toString();
		if(src.length() > compared.length())
			return 1;
		else if(src.length() < compared.length())
			return -1;
		buffCompared = src.compareTo(compared);
        return buffCompared > 0 ? 1 : (buffCompared < 0 ? -1 : 0 );
    }


    /**
    * @param BigN other
    * @return boolean - true если this больше other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isMoreThan(BigN other) {
        return this.compareTo(other) > 0;
    }

    /**
    * @param BigN other
    * @return boolean - true если this меньше other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isLessThan(BigN other) {
        return this.compareTo(other) < 0;
    }

    /**
    * @param BigN other
    * @return boolean - true если this больше или равен other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isMoreOrEquals(BigN other) {
        return this.compareTo(other) >= 0;
    }

    /**
    * @param BigN other
    * @return boolean - true если this меньше или равен other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isLessOrEquals(BigN other) {
        return this.compareTo(other) <= 0;
    }

    /**
    * @param BigN other
    * @return boolean - true если this равен other, иначе false
    *
    * @version 1
    * @author Яловега Никита
    */
    private boolean isEquals(BigN other) {
        return this.compareTo(other) == 0;
    }
	
	/**
	* Сравнение BigN, согласно спецификации Java
	*
    * @return эквивалентность
	*
	* @version 1
	* @author 
	*/
	@Override
    public boolean equals(Object otherObj) 
	{
		if (otherObj == this) return true; 
		if (otherObj == null) return false;
		if( this.getClass() != otherObj.getClass() ) return false;
		BigN other = (BigN)otherObj;
		return this.isEquals(other);
    } 


    /**
    * Проверка большого числа на 0.
    *
    * @param BigN num - число для проверки
    * @return boolean - результат проверки
    *
    * @version 1
    * @author Яловега Никита
    */
    public boolean isZero()
    {
        return this.toString().equals("0");
    }
	
	/**
    * Умножение числа на 10^x
    *
    * @param int x - степень
    * @return BigN result - результат умножения
    *
    * @version 1.1
    * @author Семенов Алексей, Деменьтев Дмитрий
    */
    public BigN multiplyBy10x(int x)
    {
		String buff = this.toString();
		if(x < 0) 
		{
			if(x*-1 >= buff.length())
				return new BigN("0");
			buff = buff.substring(0,buff.length()+x);
		}
		else if(x == 0) return this;
		else
		{
			//String repeated = "0".repeat(x); Это для java 8 не подходит
			String repeated = String.join("", Collections.nCopies(x, "0") ); // А это подходит
			buff += repeated;
		}
		BigN result = new BigN(buff);
		return result;
    }
    
	/**
	 * умножение BigN на число k
	 *
	 * @param BigN k - множитель
	 * @return BigN result - результат умножения BigN на k
	 *
	 * @version 1
	 * @author Кашапова Ольга
	*/
	public BigN multiplyByK(BigN k)
	{
		BigN result = this.multiply(k);
		return result;
	}
	
	/**
	 * вычитание из BigN другого BigN, умноженного на k(если получится положительный результат)
	 *
	 * @param BigN other - вычитаемое, BigN k - коэффициент домножения other
	 * @return BigN result - результат вычитания из this other*k
	 *
	 * @version 1
	 * @author Кашапова Ольга
	*/
	public BigN subtructByK(BigN other, BigN k) throws ArithmeticException
	{
		BigN buff = other.multiply(k);
		if(this.compareTo( buff ) >= 0 ){
			BigN result = this.subtract( buff );
            return result;
		}
		else
			throw new ArithmeticException("Вычитание невозможно в натуральных числах\n");
	}
    
	/**
    * инкремент исходного (this) большого натурального числа
    *
    * @return исходное BigN, увеличенное на 1
    *
    * @version 2
    * @author Семенов Алексей
    */
    public BigN increment()
    {
		int over, n, i, buff1, buff2;
		boolean f;
		n = this.value.size();
		for (i = 0, f = true; i < n && f ; i++)
		{
			if(this.value.get(i) + 1 >= 1000)
				this.value.set(i, 0);
			else
			{
				this.value.set(i, this.value.get(i) + 1);
				f = false;
			}
		}
		if(f)
			this.value.add(1);
		return this;
    }
	
	/**
    * Деление нацело
    *
    * @param BigN other - делитель
    * @return BigN result - результат деления нацело
    *
    * @version 1.1
    * @author Семенов Алексей, Деменьтев Дмитрий
    */
    public BigN divide(BigN other) throws ArithmeticException
    {
		BigN result = new BigN("0");
		BigN one = new BigN("1");
		BigN buffThis = this.clone();
		BigN buffOther = new BigN();
		if(other.isZero()) 
			throw new ArithmeticException("Делить на ноль нельзя!\n");
		if(this.isLessThan(other)) 
			return result;
		else if(this.isEquals(other)) 
			return result.increment();
		if(other.toString().equals("1"))
			return this;
		Integer diff = this.toString().length()-other.toString().length();
		while(diff >= 0)
		{
			buffOther = other.multiplyBy10x(diff);
			while(buffThis.isMoreOrEquals(buffOther))
			{
				buffThis = buffThis.subtract(buffOther);
				result = result.add(one.multiplyBy10x(diff));
			}
			diff--;
		}
		return result;
    }
    
    /**
    * остаток от деления
    *
    * @param BigN other - делитель
    * @return BigN result - остаток от деления this на other
    *
    * @version 1
    * @author Деменьтев Дмитрий
    */
    public BigN mod(BigN other)
    {
		BigN result = new BigN("0");
		if (this.isLessThan(other)) 
			return this.clone();
        else 
			if (this.equals(other)) 
				return result;
			else
				return result = this.subtract(other.multiply(this.divide(other)));
    }
    
    /**
    * нод(this;other)
    *
    * @param BigN other - второе число для нахождения нод
    * @return BigN result - нод(this;other)
    *
    * @version 1
    * @author Деменьтев Дмитрий
    */
    public BigN gcd(BigN other)
    {
		BigN buffThis = this.clone();
        BigN buffOther = other.clone();
		while (!buffThis.isZero() && !buffOther.isZero())
        {
            if (buffThis.isMoreThan(buffOther)) 
                buffThis = buffThis.mod(buffOther);
            else
                buffOther = buffOther.mod(buffThis);
        }
		return buffThis.add(buffOther);
    }
    
    /**
    * нок(this;other)
    *
    * @param BigN other - второе число для нахождения нок
    * @return BigN result - нок(this;other)
    *
    * @version 1
    * @author Деменьтев Дмитрий
    */
    public BigN lcm(BigN other)
    {
		return this.multiply(other).divide(this.gcd(other));
    }
	
	/**
    * Декремент исходного (this) большого натурального числа
	* Если число равно 0, то бросит исключение
	*
    * @return исходное BigN, увеличенное на 1
    *
    * @version 1
    * @author Муродов Ахмад, Цветков Иван, Хайруллов Айрат
    */
	public BigN decrement() throws ArithmeticException
	{

		boolean f;
		int n = this.value.size(), i;

		if (n == 1 && this.value.get(0) == 0) 
			throw new ArithmeticException("Декремент невозможен в натуральных числах + {0}\n");
		if (n == 1 && this.value.get(0) == 1) 
		{
			this.value.set(0, 0);
			return this;
		}

		for (i = 0, f = true; i < n && f ; i++)
		{
			if(this.value.get(i) - 1 >= 0) 
			{
				this.value.set(i, value.get(i) - 1);
				if(this.value.get(i)==0 && i == n-1) 
					this.value.remove(i);
				f = false;
			}
			else
				this.value.set(i, 999);
		}
		return this;
	}
	
	/**
    * Проверка на чётность
	*
    * @return boolean true в случае, если чётное, и false, если не чётное
    *
    * @version 1
    * @author Ручкин Даниил
    */
	public boolean isEven() 
	{
		if( this.value.get(0) % 2 == 0)
			return true;
		else
			return false;
	}
	
	/**
    * Клонирование объекта
	*
    * @return копию BigN
    *
    * @version 1
    * @author
    */
	@Override
	public BigN clone() 
	{
		BigN result = new BigN();
		result.value = new ArrayList<Integer>(this.value);
		return result;
	}
	
	/**
    * Конвертация в BigZ
    *
    * @return BigZ result - целое число
    *
    * @version 1
    * @author Семенов Алексей
    */
    public BigZ toBigZ()
    {
		return new BigZ(this);
    }

	/**
    * Конвертация в BigQ
    *
    * @return BigQ result - рациональное число
    *
    * @version 1
    * @author
    */
    public BigQ toBigQ()
    {
		return new BigQ(this.toBigZ(), new BigZ("1"));
    }

	/**
    * Возведение в степень с помощью алгоритма бинарного возведения в степень
    *
    * @param BigN other - число, в степень которого возводится исходное
	*
    * @return BigN result - результат возведения исходного в степень other
    *
    * @version 1
    * @author
    */
	public BigN pow(BigN other) 
	{
        BigN TWO = new BigN("2");
        BigN result = new BigN("1");
        BigN base = this.clone();
        BigN b = other.clone();
        while( !b.isZero() )
        {
            if( b.isEven() == false)
                result = result.multiply(base);
            base = base.multiply(base);
            b = b.divide(TWO);
        }
        return result;
    }
    
	/**
    * Возведение в степень по модулю с помощью алгоритма бинарного возведения в степень
    *
    * @param BigN other - число, в степень которого возводится исходное
    * @param BigN p - модуль
	*
    * @return BigN result - результат возведения исходного в степень other по модулю p
    *
    * @version 1
    * @author
    */
	public BigN modPow(BigN other, BigN p) 
	{
        BigN TWO = new BigN("2");
        BigN result = new BigN("1");
        BigN base = this.clone();
        BigN b = other.clone();
        while( !b.isZero() )
        {
            if( b.isEven() == false)
            {
                result = result.multiply(base);
                result = result.mod(p);
            }
            base = base.multiply(base);
            base = base.mod(p);
            b = b.divide(TWO);
        }
        return result;
    }
    
	/**
    * Факториал
	*
    * @return BigN result - факториал числа
    *
    * @version 1
    * @author
    */
	public BigN factorial() 
	{
        BigN result = new BigN("1");
        BigN i;
        for(i = new BigN("1"); i.compareTo(this) <= 0; i.increment())
            result = result.multiply(i);
        return result;
	}

    /**
     * Вычисление первой цифры деления большего натурального на меньшее, домноженное на 10^k
     *
     * @param BigN other - делитель, домноженный на 10^k; int k - степень other
     * @return BigN result - первая цифра результата деления числа на other, домноженного на 10^k
     *
     * @version 0.0003
     * @author Соболев Матвей
     */
    public BigN divideByOtherTen(BigN other, int k)
    {
        BigN newOther = other.multiplyBy10x(k);
        BigN result = this.divide(newOther);
        String resultString = result.toString().substring(0, 1);
        result = new BigN(resultString);
        return result;
    }
}
 
 