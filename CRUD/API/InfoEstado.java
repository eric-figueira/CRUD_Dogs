package CRUD.API;

public class InfoEstado implements Cloneable
{
    private String nome;
    public String getNome ()
    {
        return this.nome;
    }
    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.length()==0)
            throw new Exception ("Nome ausente");

        this.nome = nome;
    }

    private String codigo_ibge;
    public  String getCodigo_ibge ()
    {
        return this.codigo_ibge;
    }
    public void setCodigo_ibge (String codigoIBGE) throws Exception
    {
        if (codigoIBGE==null || codigoIBGE.length()==0)
            throw new Exception ("Codigo do IBGE ausente");

        this.codigo_ibge = codigoIBGE;
    }

    private String area_km2;
    public  String getArea_km2 ()
    {
        return this.area_km2;
    }
    public void setArea_km2 (String areaEmKm2) throws Exception
    {
        if (areaEmKm2==null || areaEmKm2.length()==0)
            throw new Exception ("Area ausente");

        this.area_km2 = areaEmKm2;
    }

    public InfoEstado (String nome, String codigoIBGE, String areaEmKm2) throws Exception
    {
        this.setNome        (nome);
        this.setCodigo_ibge (codigoIBGE);
        this.setArea_km2    (areaEmKm2);
    }

    // exigencia do mapeador de JSon
    public InfoEstado () {}

    public String toString ()
    {
        return this.nome+
               " / Codigo IBGE: "+
               this.codigo_ibge+
               " / Area(km2): "+
               this.area_km2;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

      //if (!(this.getClass() != obj.getClass())
      //if (!(obj.getClass != InfoEstado.class))
        if (!(obj instanceof InfoEstado))
            return false;

        InfoEstado infoEstado = (InfoEstado)obj;

        if (!this.nome.equals(infoEstado.nome))
            return false;

        if (!this.codigo_ibge.equals(infoEstado.codigo_ibge))
            return false;

        if (!this.area_km2.equals(infoEstado.area_km2))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=1;

        ret = 2*ret + this.nome       .hashCode();
        ret = 2*ret + this.codigo_ibge.hashCode();
        ret = 2*ret + this.area_km2   .hashCode();

        return ret;
    }

    public InfoEstado (InfoEstado modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo inexistente");

        this.nome        = modelo.nome;
        this.codigo_ibge = modelo.codigo_ibge;
        this.area_km2    = modelo.area_km2;
    }

    public Object clone ()
    {
        InfoEstado ret=null;

        try
        {
            ret = new InfoEstado (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
