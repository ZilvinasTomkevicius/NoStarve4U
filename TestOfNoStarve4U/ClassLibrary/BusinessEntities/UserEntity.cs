using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessEntities
{
    public class UserEntity
    {
        public int UserID { get; set; }
        public string Name { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public DateTime LastLogin { get; set; }

        public UserEntity()
        {
        }

        public UserEntity(int userID, string name, string password, string email, DateTime lastLogin)
        {
            this.UserID = userID;
            this.Name = name;
            this.Password = password;
            this.Email = email;
            this.LastLogin = lastLogin;
        }
    }
}
