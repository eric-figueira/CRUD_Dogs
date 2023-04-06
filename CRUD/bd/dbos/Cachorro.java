package CRUD.bd.dbos;

public class Cachorro {

    // Atributos
    private int idCachorro;
    private String nome,
                   raca,
                   porte,
                   cor,
                   dono,
                   cep,
                   complemento;
    private short idade,
                  numeroCasa;
    private float peso;

    // Setters
    public void setIdCachorro(int id) throws Exception
    {
        // -1 vai indicar que não há nenhum cachorro na lista, e 0 será a 1a posição
        if (id < -1)
            throw new Exception("Id do Cachorro inválido!");

        this.idCachorro = id;
    }

    public void setNome(String nome) throws Exception
    {
        if (nome == null || nome.isEmpty())
            throw new Exception("Nome do cachorro inválido!");

        if (nome.length() > 15)
            nome = nome.substring(0, 15);

        this.nome = nome;
    }

    public void setRaca(String raca) throws Exception
    {
        if (raca == null || raca.isEmpty())
            throw new Exception("Raça do cachorro inválida!");

        if (raca.length() > 40)
            raca = raca.substring(0, 40);

        this.raca = raca;
    }

    public void setPorte(String porte) throws Exception
    {
        if (porte == null || porte.isEmpty())
            throw new Exception("Porte do cachorro inválido!");

        if (porte.length() > 15)
            porte = porte.substring(0, 15);

        this.porte = porte;
    }

    public void setCor(String cor) throws Exception
    {
        if (cor == null || cor.isEmpty())
            throw new Exception("Cor do cachorro inválida!");

        if (cor.length() > 10)
            cor = cor.substring(0, 10);

        this.cor = cor;
    }

    public void setDono(String dono) throws Exception
    {
        if (dono == null || dono.isEmpty())
            throw new Exception("Nome do dono inválido!");

        if (dono.length() > 30)
            dono = dono.substring(0, 30);

        this.dono = dono;
    }

    public void setCep(String cep) throws Exception
    {
        if (cep == null || cep.isEmpty())
            throw new Exception("CEP da residência do cachorro inválido!");

        if (cep.length() > 9)
            cep = cep.substring(0, 9);

        this.cep = cep;
    }

    public void setPeso(float peso) throws Exception
    {
        if (peso < 0F)
            throw new Exception("Peso do cachorro inválido!");

        this.peso = peso;
    }

    public void setIdade(short idade) throws Exception
    {
        if (idade < 0)
            throw new Exception("Idade do cachorro inválida!");

        this.idade = idade;
    }

    public void setNumeroCasa(short numeroCasa) throws Exception
    {
        if (numeroCasa < 0)
            throw new Exception("Número da casa do cachorro inválido!");

        this.numeroCasa = numeroCasa;
    }

    public void setComplemento(String complemento) throws Exception {
        if (complemento == null || complemento.isEmpty())
            throw new Exception("Complemento inválido!");

        if (complemento.length() > 15)
            complemento = complemento.substring(0, 15);

        this.complemento = complemento;
    }

    // Getters

    public int getIdCachorro() {
        return this.idCachorro;
    }

    public String getNome() {
        return this.nome;
    }

    public String getRaca() {
        return this.raca;
    }

    public String getPorte() {
        return this.porte;
    }

    public String getCor() {
        return this.cor;
    }

    public String getDono() {
        return this.dono;
    }

    public String getCep() {
        return this.cep;
    }

    public String getComplemento() { return this.complemento; }

    public short getIdade() {
        return this.idade;
    }

    public short getNumeroCasa() {
        return this.numeroCasa;
    }

    public float getPeso() {
        return this.peso;
    }

    // Métodos Obrigatórios

    public Cachorro(int idCachorro, String nome, String raca, short idade, float peso,
                    String porte, String cor, String dono, String cep, short numeroCasa, String complemento) throws Exception
    {
        this.setIdCachorro(idCachorro);
        this.setNome(nome);
        this.setRaca(raca);
        this.setIdade(idade);
        this.setPeso(peso);
        this.setPorte(porte);
        this.setCor(cor);
        this.setDono(dono);
        this.setCep(cep);
        this.setNumeroCasa(numeroCasa);
        this.setComplemento(complemento);
    }

    @Override
    public String toString() {
        String ret = "";

        ret += "IdCachorro....: " + this.getIdCachorro() + "\n";
        ret += "Nome Cachorro.: " + this.getNome() + "\n";
        ret += "Raça Cachorro.: " + this.getRaca() + "\n";
        ret += "Idade Cachorro: " + this.getIdade() + "\n";
        ret += "Peso Cachorro.: " + this.getPeso() + "\n";
        ret += "Porte Cachorro: " + this.getPorte() + "\n";
        ret += "Cor Cachorro..: " + this.getCor() + "\n";
        ret += "Nome Dono.....: " + this.getDono() + "\n";
        ret += "CEP...........: " + this.getCep() + "\n";
        ret += "Número Casa...: " + this.getNumeroCasa() + "\n";
        ret += "Complemento...: " + this.getComplemento();

        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (!(obj instanceof Cachorro)) return false;

        Cachorro c = (Cachorro) obj;

        if (this.idCachorro != c.idCachorro) return false;
        if (!this.nome.equals(c.nome)) return false;
        if (!this.raca.equals(c.raca)) return false;
        if (this.idade != c.idade) return false;
        if (this.peso != c.peso) return false;
        if (!this.porte.equals(c.porte)) return false;
        if (!this.cor.equals(c.cor)) return false;
        if (!this.dono.equals(c.dono)) return false;
        if (!this.cep.equals(c.cep)) return false;
        if (this.numeroCasa != c.numeroCasa) return false;
        if (!this.complemento.equals(c.complemento)) return false;

        return true;
    }

    public int hashCode() {
        int ret = 246;

        ret = 23 * ret + Integer.valueOf(this.idCachorro).hashCode();
        ret = 23 * ret + this.nome.hashCode();
        ret = 23 * ret + this.raca.hashCode();
        ret = 23 * ret + Short.valueOf(this.idade).hashCode();
        ret = 23 * ret + Float.valueOf(this.peso).hashCode();
        ret = 23 * ret + this.porte.hashCode();
        ret = 23 * ret + this.cor.hashCode();
        ret = 23 * ret + this.dono.hashCode();
        ret = 23 * ret + this.cep.hashCode();
        ret = 23 * ret + Short.valueOf(this.numeroCasa).hashCode();
        ret = 23 * ret + this.complemento.hashCode();

        if (ret < 0) ret = -ret;

        return ret;
    }

    public Cachorro(Cachorro obj) throws Exception
    {
        if (obj == null)
            throw new Exception("Modelo de objeto ausente!");

        this.idCachorro  = obj.idCachorro;
        this.nome        = obj.nome;
        this.raca        = obj.raca;
        this.idade       = obj.idade;
        this.peso        = obj.peso;
        this.porte       = obj.porte;
        this.cor         = obj.cor;
        this.dono        = obj.dono;
        this.cep         = obj.cep;
        this.numeroCasa  = obj.numeroCasa;
        this.complemento = obj.complemento;
    }

    public Object clone()
    {
        Cachorro ret = null;

        try {
            ret = new Cachorro(this);
        } catch (Exception erro) {}

        return ret;
    }
}
