package CRUD.API;

public class InfoCidade implements Cloneable
{
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
    public  String getArea_km2()
    {
        return this.area_km2;
    }
    public void setArea_km2 (String areaEmKm2) throws Exception
    {
        if (areaEmKm2==null || areaEmKm2.length()==0)
            throw new Exception ("Area ausente");

        this.area_km2 = areaEmKm2;
    }

    public InfoCidade (String codigoIBGE, String areaEmKm2) throws Exception
    {
        this.setCodigo_ibge (codigoIBGE);
        this.setArea_km2    (areaEmKm2);
    }

    // exigencia do mapeador de JSon
    public InfoCidade () {}

    public String toString ()
    {
        return "Codigo IBGE: "+
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
      //if (!(obj.getClass != InfoCidade.class))
        if (!(obj instanceof InfoCidade))
            return false;

        InfoCidade infoCidade = (InfoCidade)obj;

        if (!this.codigo_ibge.equals(infoCidade.codigo_ibge))
            return false;

        if (!this.area_km2.equals(infoCidade.area_km2))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=1;

        ret = 2*ret + this.codigo_ibge.hashCode();
        ret = 2*ret + this.area_km2   .hashCode();

        return ret;
    }

    public InfoCidade (InfoCidade modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo inexistente");

        this.codigo_ibge = modelo.codigo_ibge;
        this.area_km2    = modelo.area_km2;
    }

    public Object clone ()
    {
        InfoCidade ret=null;

        try
        {
            ret = new InfoCidade (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
