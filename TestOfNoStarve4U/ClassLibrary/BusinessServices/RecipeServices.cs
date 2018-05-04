using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;
using BusinessServices;
using BusinessEntities;

namespace BusinessServices
{
    /// <summary>
    /// A service for recipe management
    /// </summary>
    public class RecipeServices : IRecipeServices
    {
        private string connectionString;

        public RecipeServices()
        {
            this.connectionString = ClassLibrary.Properties.Settings.Default.ConnectionString;
        }
       
        private SqlDataReader reader;


        public void Add(RecipeEntity recipe)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlTransaction tr = con.BeginTransaction();

                SqlCommand cmd = new SqlCommand("insert into dbo.Recipe (Name, recDescription, cookingTime ) values (@Name, @Desc, @CookT)", con, tr);

                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@Name", recipe.Name);
                cmd.Parameters.AddWithValue("@Desc", recipe.Description);
                cmd.Parameters.AddWithValue("@CookT", recipe.CookingTime);

                cmd.ExecuteNonQuery();

                tr.Commit();
            }

        }

        public void Update(RecipeEntity recipe)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlTransaction tr = con.BeginTransaction();

                SqlCommand cmd = new SqlCommand("update Recipe set Name = @Name, recDescription = @Desc, cookingTime = @CookT where Id = @Id", con, tr);
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@Name", recipe.Name);
                cmd.Parameters.AddWithValue("@Desc", recipe.Description);
                cmd.Parameters.AddWithValue("@CookT", recipe.CookingTime);
                cmd.Parameters.AddWithValue("@Id", recipe.ID);

                cmd.ExecuteNonQuery();

                tr.Commit();
            }
        }

        public void Delete(int recipeID)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlCommand cmd = new SqlCommand("delete from Recipe where Id = @ID", con);
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@ID", recipeID);

                cmd.ExecuteNonQuery();
            }

        }

        public RecipeEntity Get(int recipeID)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlCommand cmd = new SqlCommand("select id, name, recDescription, cookingTime from Recipe where Id = @Id", con);
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@Id", recipeID);

                reader = cmd.ExecuteReader();

                RecipeEntity recipe = new RecipeEntity();

                while(reader.Read())
                {
                    recipe.ID = reader.GetInt32(0);
                    recipe.Name = reader.GetString(1);
                    recipe.Description = reader.GetString(2);
                    recipe.CookingTime = reader.GetInt32(3);
                }

                reader.Close();

                return recipe;
            }

        }

        public List<RecipeEntity> GetList()
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                List<RecipeEntity> recipeList = new List<RecipeEntity>();

                SqlCommand cmd = new SqlCommand("select id, name, recDescription, cookingTime  from Recipe", con);
                cmd.CommandType = CommandType.Text;

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

        public int GetRecipeCount()
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                int count = 0;

                SqlCommand cmd = new SqlCommand("select COUNT(*) from Recipe", con);
                cmd.CommandType = CommandType.Text;

                reader = cmd.ExecuteReader();

                while(reader.Read())
                {
                    count = reader.GetInt32(0);
                }

                reader.Close();

                return count;
            }
        }
    }
}
