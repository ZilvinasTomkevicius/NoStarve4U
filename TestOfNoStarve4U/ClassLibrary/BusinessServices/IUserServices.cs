using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BusinessEntities;

namespace BusinessServices
{
    public interface IUserServices
    {
        void Add(UserEntity user);
        void Update(UserEntity user);
        void Delete(int userID);
        UserEntity Get(int userID);
        List<UserEntity> GetList();
    }
}
