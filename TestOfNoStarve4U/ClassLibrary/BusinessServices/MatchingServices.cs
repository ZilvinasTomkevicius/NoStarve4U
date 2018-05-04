using BusinessEntities;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessServices
{
    /// <summary>
    /// A Service for getting a recipe
    /// </summary>
    public class MatchingServices : IMatchingServices
    {
        private string connectionString;

        public MatchingServices()
        {
            this.connectionString = ClassLibrary.Properties.Settings.Default.ConnectionString;
        }

        private SqlDataReader reader;

        public List<RecipeEntity> GetMatchedRecipes(List<ProductEntity> checkedProducts)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                List<RecipeEntity> recipeList = new List<RecipeEntity>();

                DataTable dataTable = new DataTable("Temp_Product");

                dataTable.Columns.Add("ID", typeof(Int32));
                dataTable.Columns.Add("Name", typeof(string));

                foreach (var product in checkedProducts)
                {
                    dataTable.Rows.Add(product.ID, product.Name);
                }

                SqlCommand cmd = new SqlCommand("Recipe_matching", con);
                cmd.CommandType = CommandType.StoredProcedure;

                SqlParameter parameter = cmd.Parameters.AddWithValue("@tp", dataTable);
                parameter.SqlDbType = SqlDbType.Structured;

                cmd.ExecuteNonQuery();

                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    RecipeEntity recipe = new RecipeEntity();

                    recipe.ID = reader.GetInt32(0);
                    recipe.Name = reader.GetString(1);
                    recipe.Description = reader.GetString(2);
                    recipe.CookingTime = reader.GetInt32(3);

                    recipeList.Add(recipe);
                }

                reader.Close();

                return recipeList;
            }
        }
     }    
 }
