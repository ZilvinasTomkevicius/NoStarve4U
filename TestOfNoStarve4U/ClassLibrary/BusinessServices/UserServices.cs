using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BusinessEntities;
using BusinessServices;
using System.Data.SqlClient;
using System.Data;

namespace ClassLibrary.BusinessServices
{
    /// <summary>
    /// A service for user management
    /// </summary>
    public class UserServices : IUserServices
    {
        private string connectionString;

        public UserServices()
        {
            this.connectionString = ClassLibrary.Properties.Settings.Default.ConnectionString;
        }

        private SqlDataReader reader;

        public void Add(UserEntity user)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                try
                {
                    con.Open();

                    SqlTransaction tr = con.BeginTransaction();

                    SqlCommand cmd = new SqlCommand("insert into [User](Name, [Password], Email) values (@Name, @Password, @Email)", con, tr);
                    cmd.CommandType = CommandType.Text;
                    cmd.Parameters.AddWithValue("@Name", user.Name);
                    cmd.Parameters.AddWithValue("@Email", user.Email);
                    cmd.Parameters.AddWithValue("@Password", user.Password);

                    cmd.ExecuteNonQuery();

                    tr.Commit();
                }
                catch (Exception e)
                {
                    throw new Exception(e.Message);
                }

            }

        }

        public void Update(UserEntity user)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                try
                {
                    con.Open();

                    SqlTransaction tr = con.BeginTransaction();

                    SqlCommand cmd = new SqlCommand("update [User] set Name = @Name, Email = @Email, [Password] = @Password where userID = @ID", con, tr);
                    cmd.CommandType = CommandType.Text;
                    cmd.Parameters.AddWithValue("@ID", user.UserID);
                    cmd.Parameters.AddWithValue("@Password", user.Password);
                    cmd.Parameters.AddWithValue("@Name", user.Name);
                    cmd.Parameters.AddWithValue("@Email", user.Email);

                    cmd.ExecuteNonQuery();

                    tr.Commit();
                }
                catch (Exception e)
                {
                    throw new Exception(e.Message);
                }
            }
        }

        public void Delete(int userID)
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlCommand cmd = new SqlCommand("delete from [User] where userID = @ID", con);
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@ID", userID);

                cmd.ExecuteNonQuery();
            }
        }

        public UserEntity Get(int userID)
        {

            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                SqlCommand cmd = new SqlCommand("select userID,Name,[Password],Email,[DateTime] from [User] where userID = @ID", con);
                cmd.CommandType = CommandType.Text;
                cmd.Parameters.AddWithValue("@ID", userID);

                reader = cmd.ExecuteReader();

                UserEntity user = new UserEntity();

                while (reader.Read())
                {
                    user.UserID = reader.GetInt32(0);
                    user.Name = reader.GetString(1);
                    user.Password = reader.GetString(2);
                    user.Email = reader.GetString(3);
                    user.LastLogin = reader.GetDateTime(4);
                }

                reader.Close();

                return user;
            }

        }

        public List<UserEntity> GetList()
        {
            using (SqlConnection con = new SqlConnection(this.connectionString))
            {
                con.Open();

                List<UserEntity> userList = new List<UserEntity>();

                SqlCommand cmd = new SqlCommand("select userID, Name, Email, [Password], [DateTime] from [User]", con);
                cmd.CommandType = CommandType.Text;

                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    UserEntity user = new UserEntity();

                    user.UserID = reader.GetInt32(0);
                    user.Name = reader.GetString(1);
                    user.Email = reader.GetString(2);
                    user.Password = reader.GetString(3);
                    user.LastLogin = reader.GetDateTime(4);

                    userList.Add(user);
                }

                reader.Close();

                return userList;
            }
        }
    }
}
