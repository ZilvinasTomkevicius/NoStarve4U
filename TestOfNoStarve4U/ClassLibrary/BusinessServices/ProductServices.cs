using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BusinessServices;
using BusinessEntities;

namespace BusinessServices
{
    /// <summary>
    /// A service for product management
    /// </summary>
    public class ProductServices : IProductServices
    {
        private string connectionString;

        public ProductServices()
        {
            this.connectionString = ClassLibrary.Properties.Settings.Default.ConnectionString;
        }

        private SqlDataReader reader;

        public void Add(ProductEntity product)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                try
                {
                    con.Open();

                    SqlTransaction tr = con.BeginTransaction();

                    SqlCommand cmd = new SqlCommand("insert into dbo.Product(Name, Kind) values (@Name, @Kind)", con, tr);
                    cmd.CommandType = CommandType.Text;
                    cmd.Parameters.AddWithValue("@Name", product.Name);
                    cmd.Parameters.AddWithValue("@Kind", product.Kind);

                    cmd.ExecuteNonQuery();

                    tr.Commit();
                }
                catch(Exception e)
                {
                    throw new Exception(e.Message);
                }
                
            }
                        
        }

        public void Update(ProductEntity product)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                try
                {
                    con.Open();

                    SqlTransaction tr = con.BeginTransaction();

                    SqlCommand cmd = new SqlCommand("update Product set kind = @Kind, name=@name where id = @Id", con, tr);
                    cmd.CommandType = CommandType.Text;
                    cmd.Parameters.AddWithValue("@Name", product.Name);
                    cmd.Parameters.AddWithValue("@Kind", product.Kind);
                    cmd.Parameters.AddWithValue("@Id", product.ID);

                    cmd.ExecuteNonQuery();

                    tr.Commit();
                }
                catch(Exception e)
                {
                    throw new Exception(e.Message);
                }
            }
        }

        public void Delete(int productID)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlCommand cmd = new SqlCommand("delete from Product where id = @Id", con);
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@Id", productID);

                cmd.ExecuteNonQuery();
            }   
        }

        public ProductEntity Get(int productID)
        {

            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlCommand cmd = new SqlCommand("select id,name,kind  from Product where Id = @id", con);
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@Id", productID);

                reader = cmd.ExecuteReader();

                ProductEntity product = new ProductEntity();

                while (reader.Read())
                {
                    product.ID = reader.GetInt32(0);
                    product.Name = reader.GetString(1);
                    product.Kind = reader.GetString(2);
                }

                reader.Close();

                return product;
            }

        }

        public List<ProductEntity> GetList()
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                List<ProductEntity> productList = new List<ProductEntity>();

                SqlCommand cmd = new SqlCommand("select id, name, kind from Product", con);
                cmd.CommandType = CommandType.Text;

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    ProductEntity product = new ProductEntity();

                    product.ID = reader.GetInt32(0);
                    product.Name = reader.GetString(1);
                    product.Kind = reader.GetString(2);

                    productList.Add(product);
                }

                reader.Close();

                return productList;
            }
        }

    }
}
