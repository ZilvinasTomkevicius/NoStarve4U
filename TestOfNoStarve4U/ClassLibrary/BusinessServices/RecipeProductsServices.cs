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
    /// This class is responsible for recipe products manegement
    /// </summary>
    public class RecipeProductsServices : IRecipeProductServices
    {
        private string connectionString;

        public RecipeProductsServices()
        {
            this.connectionString = ClassLibrary.Properties.Settings.Default.ConnectionString;
        }

        private SqlDataReader reader;

        public void Add(RecipeProductsEntity recipeProductsEntity)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlTransaction tr = con.BeginTransaction();
                
                foreach(int ProdID in recipeProductsEntity.ProductIDList)
                {
                    SqlCommand cmd = new SqlCommand("insert into dbo.Recipe_Products(RecID, ProdID) values(@RecID, @ProdID)", con, tr);

                    cmd.CommandType = CommandType.Text;

                    cmd.Parameters.AddWithValue("@RecID", recipeProductsEntity.RecipeID);
                    cmd.Parameters.AddWithValue("@ProdID", ProdID);

                    cmd.ExecuteNonQuery();
                }
              
                tr.Commit();
            }
        }

        public RecipeProductsEntity Get(int recipeID)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlCommand cmd = new SqlCommand("select ProdID from Recipe_Products where RecID = @ID", con);
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@ID", recipeID);

                reader = cmd.ExecuteReader();

                List<int> productIDList = new List<int>();

                while (reader.Read())
                {
                    int ProductID = reader.GetInt32(0);

                    productIDList.Add(ProductID);
                }

                RecipeProductsEntity recipeProductsEntity = new RecipeProductsEntity(recipeID, productIDList);

                reader.Close();

                return recipeProductsEntity;
            }
        }
    }
}
