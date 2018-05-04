using BusinessEntities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BusinessServices
{
    public interface IProductServices
    {
        void Add(ProductEntity product);
        void Update(ProductEntity product);
        void Delete(int productID);
        ProductEntity Get(int productID);
        List<ProductEntity> GetList();
    }
}
